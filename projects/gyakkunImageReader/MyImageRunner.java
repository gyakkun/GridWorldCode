import imagereader.Runner;
//import imagereader.IImageIO;
//import imagereader.IImageProcessor;

public final class MyImageRunner {

    private MyImageRunner() {}
    public static void main(String[] args) {
        MyImageIO ioInstance = new MyImageIO();
        ImageProcessor processor = new ImageProcessor();
        Runner.run(ioInstance, processor);
    }
}
