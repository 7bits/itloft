package utils.validators.impl;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import utils.validators.ImageSize;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

/**
 * Annotation {@link utils.validators.ImageSize} implementation
 * Checks width and height of image
 */
public class ImageSizeCheck extends AbstractAnnotationCheck<ImageSize> {

    /** Error message key. */
    public final static String message = "validation.image-size";

    /** Width required by validation. */
    private Integer width;

    /** Height required by validation. */
    private Integer height;

    /**
     * Reads input parameters
     * @param imageSize annotation
     */
    @Override
    public void configure(final ImageSize imageSize) {
        setMessage(imageSize.message());
        this.width = imageSize.width();
        this.height = imageSize.height();
    }

    /***
     * Puts message variables in Map
     * @return map with message variables: width and height
     */
    @Override
    protected Map<String, ?> createMessageVariables() {
        final Map<String, String> variables = new TreeMap<String, String>();
        variables.put("2", width.toString());
        variables.put("3", height.toString());
        return variables;
    }

    /**
     * Validates image size
     * @param validatedObject    Validated object
     * @param value              File
     * @param oValContext
     * @param validator
     * @return true if image size passes validation
     * @throws OValException
     */
    @Override
    public boolean isSatisfied(
            final Object validatedObject,
            final Object value,
            final OValContext oValContext,
            final Validator validator
    ) throws OValException {

        if (value == null) {
            return true;
        }

        requireMessageVariablesRecreation();
        BufferedImage readImage;
        Integer imageHeight = null;
        Integer imageWidth = null;
        try {
            readImage = ImageIO.read((File) value);
            imageHeight = readImage.getHeight();
            imageWidth = readImage.getWidth();
        } catch (Exception e) {
            readImage = null;
        }

        Boolean isSatisfied = true;
        if (width != null) {
            isSatisfied = imageWidth != null && imageWidth.equals(this.height);
        }
        if (height != null) {
            isSatisfied = isSatisfied && imageHeight != null && imageHeight.equals(this.height);
        }

        return  isSatisfied;
    }
}
