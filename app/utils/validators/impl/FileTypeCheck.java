package utils.validators.impl;

import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AbstractAnnotationCheck;
import net.sf.oval.context.OValContext;
import net.sf.oval.exception.OValException;
import play.templates.JavaExtensions;
import utils.validators.FileType;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Annotation {@link utils.validators.FileType} implementation
 * Checks file type
 */
public class FileTypeCheck extends AbstractAnnotationCheck<FileType> {

    /** Error message key. */
    public final static String message = "validation.file-type";

    /** File types required by validation. */
    private List<String> fileTypes;

    /**
     * Reads input parameters
     * @param fileType annotation
     */
    @Override
    public void configure(final FileType fileType) {
        setMessage(fileType.message());
        this.fileTypes = Arrays.asList(fileType.types());
    }

    /***
     * Puts message variables in Map
     * @return map with message variable: file types
     */
    @Override
    protected Map<String, ?> createMessageVariables() {
        final Map<String, String> variables = new TreeMap<String, String>();
        variables.put("2", JavaExtensions.join(fileTypes, ", "));
        return variables;
    }

    /**
     * Validates file type
     * @param validatedObject    Validated object
     * @param value              File
     * @param oValContext
     * @param validator
     * @return true if file passes type validation
     * @throws net.sf.oval.exception.OValException
     */
    @Override
    public boolean isSatisfied(
            final Object validatedObject,
            final Object value,
            final OValContext oValContext,
            final Validator validator
    ) throws OValException {

        requireMessageVariablesRecreation();
        String mimeType = new MimetypesFileTypeMap().getContentType((File) value);

        return fileTypes == null ||
                fileTypes.size() == 0 ||
                fileTypes.contains(mimeType);
    }
}
