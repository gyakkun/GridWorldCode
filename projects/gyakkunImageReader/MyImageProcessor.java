import imagereader.IImageProcessor;
import sun.awt.image.ToolkitImage;

import java.awt.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;


public class MyImageProcessor {

    class RedSwapFilter extends RGBImageFilter {
        public int filterRGB(int x, int y, int rgb) {
            return rgb & 0xffff0000;
        }
    }

    public Image showChanelRed(Image img) {
        RedSwapFilter red = new RedSwapFilter();
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        img.getSource(),
                        red));
    }

    class GreenSwapFilter extends RGBImageFilter {
        public int filterRGB(int x, int y, int rgb) {
            return rgb & 0xff00ff00;
        }
    }

    public Image showChanelGreen(Image img) {
        GreenSwapFilter green = new GreenSwapFilter();
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        img.getSource(),
                        green));
    }

    class BlueSwapFilter extends RGBImageFilter {
        public int filterRGB(int x, int y, int rgb) {
            return rgb & 0xff0000ff;
        }
    }

    public Image showChanelBlue(Image img) {
        BlueSwapFilter blue = new BlueSwapFilter();
        return Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(
                        img.getSource(),
                        blue));
    }


}
