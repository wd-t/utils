package org.wdt.utils.gson.typeadapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.File;
import java.io.IOException;

public class FileTypeAdapter extends TypeAdapter<File> {
    @Override
    public void write(JsonWriter out, File value) throws IOException {
        if (value != null) {
            out.value(value.getCanonicalPath());
        } else {
            out.nullValue();
        }
    }

    @Override
    public File read(JsonReader in) throws IOException {
        String path;
        if (in.peek() == JsonToken.BEGIN_OBJECT) {
            in.beginObject();
            in.nextName();
            path = in.nextString();
            in.endObject();
        } else {
            path = in.nextString();
        }
        return new File(path);
    }
}
