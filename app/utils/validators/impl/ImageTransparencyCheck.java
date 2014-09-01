package utils.validators.impl;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import utils.validators.ImageTransparency;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Annotation {@link utils.validators.ImageTransparency} implementation
 * Checks if image have any transparency
 */
public class ImageTransparencyCheck extends AbstractAnnotationCheck<ImageTransparency> {

    /** Error message key. */
    public final static String message = "validation.image-transparency";

    /**
     * Reads input parameters
     * @param imageTransparency annotation
     */
    @Override
    public void configure(final ImageTransparency imageTransparency) {
        setMessage(imageTransparency.message());
    }

    /**
     * Validates image transparency occurrence
     * @param validatedObject    Validated object
     * @param value              File
     * @param oValContext
     * @param validator
     * @return true if image have transparency
     * @throws OValException
     */
    @Override
    public boolean isSatisfied(
            final Object validatedObject,
            final Object value,
            final OValContext oValContext,
            final Validator validator
    ) throws OValException {

        BufferedImage readImage;
        Boolean isSatisfied = false;
        try {
            readImage = ImageIO.read((File) value);
            isSatisfied = readImage.getColorModel().hasAlpha();
        } catch (Exception e) {
            readImage = null;
        }

        return isSatisfied;
    }
}
