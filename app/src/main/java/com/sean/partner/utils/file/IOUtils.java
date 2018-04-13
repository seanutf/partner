package com.sean.partner.utils.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

public class IOUtils {
	
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

	public static void write(String data, OutputStream output, String encoding) throws IOException{
        if (data != null) {
            if (encoding == null) {
                write(data, output);
            } else {
                output.write(data.toString().getBytes(encoding));
            }
        }
	}

	private static void write(String data, OutputStream output) throws IOException {
        if (data != null) {
            output.write(data.toString().getBytes());
        }
	}
	
    public static void closeQuietly(OutputStream output) {
        try {
            if (output != null) {
                output.close();
            }
        } catch (IOException ioe) {
            output = null;
        }
    }

	public static String toString(InputStream in, String encoding) throws IOException {
        StringWriter sw = new StringWriter();
        copy(in, sw, encoding);
        return sw.toString();
	}

    public static void copy(InputStream input, Writer output, String encoding)
            throws IOException {
        if (encoding == null) {
        	InputStreamReader in = new InputStreamReader(input);
            copy(in, output);
        } else {
            InputStreamReader in = new InputStreamReader(input, encoding);
            copy(in, output);
        }
    }
    
    public static int copy(Reader input, Writer output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }
    
    public static long copyLarge(Reader input, Writer output) throws IOException {
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
    
    public static int copy(InputStream input, OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }    
    
    public static long copyLarge(InputStream input, OutputStream output)
            throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }
    
	public static void closeQuietly(InputStream in) {
        try {
            if (in != null) {
            	in.close();
            }
        } catch (IOException ioe) {
        	in = null;
        }		
	}
	
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }
}
