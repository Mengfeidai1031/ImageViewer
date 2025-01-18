package software.ulpgc.imageviewer.view;

import software.ulpgc.imageviewer.model.Image;
import java.awt.event.ActionListener;

public interface ImageViewerDisplay {
    void display(Image image);
    void onPrevious(ActionListener listener);
    void onNext(ActionListener listener);
}
