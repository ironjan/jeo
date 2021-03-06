/* Copyright 2013 The jeo project. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jeo.postgis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import io.jeo.vector.Feature;
import io.jeo.vector.FeatureAppendCursor;
import io.jeo.vector.FeatureWriteCursor;
import io.jeo.vector.ListFeature;

public class PostGISAppendCursor extends FeatureAppendCursor {

    PostGISDataset dataset;
    Connection cx;

    Feature next;

    PostGISAppendCursor(PostGISDataset dataset, Connection cx) {
        this.dataset = dataset;
        this.cx = cx;
    }

    @Override
    public Feature next() throws IOException {
        return next = new ListFeature(dataset.schema());
    }

    @Override
    public FeatureWriteCursor write() throws IOException {
        dataset.doInsert(next, cx);
        return this;
    }

    @Override
    public void close() throws IOException {
        if (cx != null) {
            try {
                cx.close();
            } catch (SQLException e) {
                throw new IOException(e);
            }
        }
        cx = null;
    }
    

}
