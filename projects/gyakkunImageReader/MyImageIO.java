import imagereader.IImageIO;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyImageIO implements IImageIO {
    private final static int BF_HEAD_BYTE = 14;
    private final static int BF_INFO_BYTE = 40;
    private final static int FOUR_BYTE = 4;
    private final static int MULTICOLOUR = 24;
    private final static int GRAY = 8;
    private int bitCount;

    public Image myRead(String arg0) throws IOException {
        FileInputStream file = new FileInputStream(arg0);
        byte bHead[] = new byte[BF_HEAD_BYTE];
        byte bInfo[] = new byte[BF_INFO_BYTE];
        byte originalRGB[];
        int width, height, imageSize, npad, pixelSize, offset;
        int RGBDate[];
        Image image = null;

        try {
            file.read(bHead, 0, BF_HEAD_BYTE);
            file.read(bInfo, 0, BF_INFO_BYTE);

            // Offset in file format spec
            offset = (((int) bHead[13] & 0xff) << 24)
                    | (((int) bHead[12] & 0xff) << 16)
                    | (((int) bHead[11] & 0xff) << 8)
                    | (int) bHead[10] & 0xff;

            //The actual location of binary code minus 54 Byte(bHead + bInfo)
            //Equals the starting address of actual bitmap matrix.
            //Store this address into @offset
            offset -= 54;

            //Width of bitmap
            width = (((int) bInfo[7] & 0xff) << 24)
                    | (((int) bInfo[6] & 0xff) << 16)
                    | (((int) bInfo[5] & 0xff) << 8)
                    | (int) bInfo[4] & 0xff;

            //Height of bitmap
            height = (((int) bInfo[11] & 0xff) << 24)
                    | (((int) bInfo[10] & 0xff) << 16)
                    | (((int) bInfo[9] & 0xff) << 8)
                    | (int) bInfo[8] & 0xff;

            //Total bits of bitmap
            bitCount = (((int) bInfo[15] & 0xff) << 8) | (int) bInfo[14] & 0xff;

            //The actual image (bitmap) size occupying in disk
            //rather than the size of file.
            imageSize = (((int) bInfo[23] & 0xff) << 24)
                    | (((int) bInfo[22] & 0xff) << 16)
                    | (((int) bInfo[21] & 0xff) << 8)
                    | (int) bInfo[20] & 0xff;


            if (bitCount == MULTICOLOUR) {
                //Calculate if there's empty bytes due to
                //mod 4
                npad = (imageSize / height) - width * 3;
                if (npad == FOUR_BYTE) {
                    npad = 0;
                }

                //Calculate the pixel size
                pixelSize = (width + npad) * 3 * height;

                if (npad != 0) {
                    originalRGB = new byte[pixelSize];
                } else {
                    originalRGB = new byte[imageSize];
                }

                //Read in all RGB value.
                file.read(originalRGB, 0, pixelSize);
                RGBDate = new int[height * width];

                int index = 0;
                for (int j = 0; j < height; j++) {
                    for (int i = 0; i < width; i++) {
                        RGBDate[width * (height - j - 1) + i] =
                                (255 & 0xff) << 24
                                        | (((int) originalRGB[index + 2] & 0xff) << 16)
                                        | (((int) originalRGB[index + 1] & 0xff) << 8)
                                        | (int) originalRGB[index] & 0xff;
                        index += 3;
                    }
                    index += npad;
                }

                image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, RGBDate, 0, width));
            }

            if (bitCount == GRAY) {
                //Calculate if there's empty bytes due to
                //mod 4
                npad = (imageSize / height) - width;
                if (npad == FOUR_BYTE) {
                    npad = 0;
                }

                //Calculate the pixel size
                pixelSize = (width + npad) * height;

                if (npad != 0) {
                    originalRGB = new byte[pixelSize];
                } else {
                    originalRGB = new byte[imageSize];
                }

                originalRGB = new byte[pixelSize];

                //Read in all RGB value.
                file.read(originalRGB, 0, pixelSize);
                RGBDate = new int[height * width];

                int index = offset;
                for (int j = 0; j < height; j++) {
                    for (int i = 0; i < width; i++) {
                        if (index >= pixelSize)
                            index = 0;
                        RGBDate[width * (height - j - 1) + i] =
                                (255 & 0xff) << 24
                                        | (((int) originalRGB[index] & 0xff) << 16)
                                        | (((int) originalRGB[index] & 0xff) << 8)
                                        | (int) originalRGB[index] & 0xff;
                        index += 1;
                    }
                    index += npad;
                }

                image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(width, height, RGBDate, 0, width));
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return image;
    }

    public Image myWrite(Image image, String file) throws IOException {
        try {
            int height = image.getHeight(null);
            int width = image.getWidth(null);
            int fileType;

            if (bitCount == MULTICOLOUR)
                fileType = BufferedImage.TYPE_3BYTE_BGR;
            else
                fileType = BufferedImage.TYPE_BYTE_GRAY;

            //Create an image
            BufferedImage bufferImg = new BufferedImage(width, height, fileType);
            bufferImg.getGraphics().drawImage(image, 0, 0, null);

            //File open handle
            File iFile = new File(file + ".bmp");
            ImageIO.write(bufferImg, "bmp", iFile);
        } catch (Exception err) {
            err.printStackTrace();
        }

        return image;
    }
}
