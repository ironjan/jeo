package org.jeo.data;

import java.io.IOException;
import java.util.Iterator;

import org.jeo.feature.Schema;

/**
 * A container of {@link Dataset} objects.
 * 
 * @author Justin Deoliveira, OpenGeo
 */
public interface Workspace extends Disposable {

    /**
     * The driver used to open the workspace.
     */
    Driver<?> getDriver();

    /**
     * The names of all layers of the workspace.
     * 
     * @return Iterator over layer names.
     */
    Iterator<String> layers() throws IOException;

    /**
     * Returns a layer object by name.
     * 
     * @param layer Name of the layer.
     * 
     * @return The Layer object, or <code>null</code> if no such layer exists.
     */
    Dataset get(String layer) throws IOException;

    /**
     * Creates a new vector layer in the workspace.
     * <p>
     * This method should throw {@link UnsupportedOperationException} if the workspace is not 
     * capable of creating new vector layers.
     * </p>
     * @param schema The schema of the vector layer.
     * 
     */
    Vector create(Schema schema) throws IOException;

    /**
     * Disposes the workspace.
     * <p>
     * Application code should always call this method when the workspace is no longer needed. 
     * </p>
     */
    void dispose();
}
