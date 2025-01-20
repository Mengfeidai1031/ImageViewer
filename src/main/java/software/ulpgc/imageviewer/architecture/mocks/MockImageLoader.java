package software.ulpgc.imageviewer.architecture.mocks;

import software.ulpgc.imageviewer.architecture.model.Image;
import software.ulpgc.imageviewer.architecture.model.ImageLoader;

public class MockImageLoader implements ImageLoader {
    private final String[] ids = new String[]{"red", "green", "blue"};

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(int i) {
        return new Image() {
            @Override
            public String name() {
                return ids[i];
            }

            @Override
            public byte[] content() {
                return new byte[0];
            }

            @Override
            public Format format() {
                return Format.png;
            }

            @Override
            public Image next() {
                return imageAt((i + 1) % ids.length);
            }

            @Override
            public Image previous() {
                return imageAt(i > 0 ? i - 1 : ids.length - 1);
            }
        };
    }
}
