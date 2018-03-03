package com.migcomponents.migbase64;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import junit.framework.TestCase;

import static org.junit.Assert.assertArrayEquals;


/**
 * @author shamilbi shamilbi@users.sourceforge.net
 *
 */
public class CompareTest extends TestCase
{

    private static final int KBYTES = 1024;

    private final File testDir = new File("test");

    private final File inFile = new File(testDir, "in");

    private final File migbase64File = new File(testDir, "mig.base64");

    private final File streamFile = new File(testDir, "stream.base64");

    private final File[] files = new File[] { inFile, migbase64File, streamFile };

    protected void setUp() throws Exception {
        testDir.mkdirs();
        FileOutputStream out = new FileOutputStream(inFile);
        Random random = new Random(new Date().getTime());
        byte[] bytes = new byte[1024];
        int i = 0;
        while (i++ < KBYTES)
        {
            random.nextBytes(bytes);
            out.write(bytes);
        }
        out.close();
    }

    protected void tearDown() {
        for (int i = 0; i < files.length; i++)
        {
            files[i].delete();
        }
    }

    /**
     * @see Base64IO#encode(java.io.InputStream, java.io.OutputStream,
     *      boolean)
     * @see Base64#encodeToByte(byte[], boolean)
     * @throws IOException
     */
    public void test01() throws IOException {
        FileInputStream in = new FileInputStream(inFile);
        FileOutputStream out = new FileOutputStream(streamFile);
        try
        {
            Base64IO.encode(in, out, false);
        } finally
        {
            in.close();
            out.close();
        }

        final byte[] inBytes = readFileToByteArray(inFile);
        final byte[] migBase64 = Base64.encodeToByte(inBytes, false);
        writeByteArrayToFile(migbase64File, migBase64);

        FileInputStream in1 = new FileInputStream(streamFile);
        FileInputStream in2 = new FileInputStream(migbase64File);
        try
        {
            assertTrue(contentEquals(in1, in2));
        } finally
        {
            in1.close();
            in2.close();
        }

        final byte[] encodedBytes = readFileToByteArray(migbase64File);
        final byte[] decodedBytes = Base64.decode(encodedBytes);
        assertArrayEquals(inBytes, decodedBytes);
    }

    private static byte[] readFileToByteArray(File file) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        byte[] bytes = new byte[(int) raf.length()];
        raf.readFully(bytes);
        return bytes;
    }

    private static void writeByteArrayToFile(File file, byte[] bytes) throws IOException {
        new RandomAccessFile(file, "rw").write(bytes);
    }

    private static boolean contentEquals(InputStream in1, InputStream in2) throws IOException {
        byte[] b1 = new byte[1024];
        byte[] b2 = new byte[1024];
        int bytesRead;
        while ((bytesRead = in1.read(b1)) > 0) {
            if (bytesRead != in2.read(b2))
                return false;

            if (!Arrays.equals(b1, b2))
                return false;
        }
        return true;
    }

}
