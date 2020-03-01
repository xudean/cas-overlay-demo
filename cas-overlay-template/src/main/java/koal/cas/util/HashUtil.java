package koal.cas.util;


import kl.crypto.gb.sm3.Sm3DigestHS;
import kl.security.util.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.util.Args;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;

import static kl.i18n.I18n.tr;

/**
 * Created by gaodq on 2016/12/19.
 */
public class HashUtil {

    public static String sm3AndHex(PublicKey publicKey) {

        Args.notNull(publicKey, "publicKey");

        Sm3DigestHS sm3 = new Sm3DigestHS();
        sm3.init();
        sm3.update(publicKey.getEncoded());
        byte[] hash = sm3.dofinal();
        return Hex.encodeHexString(hash);
    }

    public static byte[] sm3(String string) {

        Args.notNull(string, "string");

        Sm3DigestHS sm3 = new Sm3DigestHS();
        sm3.init();
        sm3.update(string.getBytes(Charset.forName("UTF-8")));
        return sm3.dofinal();
    }

    public static String sm3AndBase64(String string) {
        return new String(Base64.encode(sm3(string)));
    }

    public static String sm3AndHex(String string) {
        return Hex.encodeHexString(sm3(string));
    }

    public static String sha1AndBase64(String string) {
        Args.notNull(string, "string");
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        messageDigest.update(string.getBytes(Charset.forName("UTF-8")));
        byte[] hash = messageDigest.digest();
        return new String(Base64.encode(hash));
    }

    public static String sha1AndHex(byte[] encryptParam) throws Exception {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("digest.calculation.error", e);
        }
        byte[] mdBytes = messageDigest.digest(encryptParam);
        return Hex.encodeHexString(mdBytes);
    }

    /**
     * 计算文件的摘要
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static String sm3AndHex(File file) throws IOException {
        //@formatter:off
        try (InputStream inputStream = Files.newInputStream(file.toPath())) {
            byte[] buffer = new byte[1024];
            koal.security.gb.sm3.Sm3DigestHS sm3 = new koal.security.gb.sm3.Sm3DigestHS();
            sm3.init();
            int read = 0;
            while ((read = inputStream.read(buffer)) > 0) {
                sm3.update(Arrays.copyOf(buffer, read));
            }

            byte[] hash = sm3.dofinal();
            return Hex.encodeHexString(hash);
        }
        //@formatter:on
    }


}
