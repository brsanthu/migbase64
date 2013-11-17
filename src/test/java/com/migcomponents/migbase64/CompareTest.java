package com.migcomponents.migbase64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import com.migcomponents.migbase64.Base64;
import com.migcomponents.migbase64.Base64IO;

import junit.framework.TestCase;

//import org.apache.commons.io.FileUtils;
//import org.apache.commons.io.IOUtils;


/* NOTE! Test commented out by Mikael Grev since it has a dependency on Apache Commons.*/

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

    protected void setUp() throws Exception
    {
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

    protected void tearDown() throws Exception
    {
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
    public void test01() throws IOException
    {
//        FileInputStream in = new FileInputStream(inFile);
//        FileOutputStream out = new FileOutputStream(streamFile);
//        try
//        {
//            Base64IO.encode(in, out, false);
//        } finally
//        {
//            in.close();
//            out.close();
//        }
//
//        final byte[] inBytes = FileUtils.readFileToByteArray(inFile);
//        final byte[] migBase64 = Base64.encodeToByte(inBytes, false);
//        FileUtils.writeByteArrayToFile(migbase64File, migBase64);
//
//        FileInputStream in1 = new FileInputStream(streamFile);
//        FileInputStream in2 = new FileInputStream(migbase64File);
//        try
//        {
//            assertTrue(IOUtils.contentEquals(in1, in2));
//        } finally
//        {
//            in1.close();
//            in2.close();
//        }
//
//        final byte[] encodedBytes = FileUtils.readFileToByteArray(migbase64File);
//        final byte[] decodedBytes = Base64.decode(encodedBytes);
//        assertTrue(java.util.Arrays.equals(inBytes, decodedBytes));
    }

}