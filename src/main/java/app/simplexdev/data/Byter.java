package app.simplexdev.data;

import java.nio.charset.Charset;
import java.util.Base64;

public final class Byter
{
    private Byter()
    {
        throw new AssertionError();
    }

    public static byte[] decode(final String s)
    {
        return Base64.getDecoder().decode(s);
    }

    public static String encode(final byte[] bytes)
    {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String read(final byte[] bytes, final Charset charset)
    {
        return new String(bytes, charset);
    }

    public static byte[] write(final String s, final Charset charset)
    {
        return s.getBytes(charset);
    }

    public static byte shift(final byte b, final int shift)
    {
        return (byte) (b >> shift);
    }

    public static byte unsignedShift(final byte b, final int shift)
    {
        return (byte) (b >>> shift);
    }

    public static byte preservedShift(final byte b, final int shift)
    {
        final int preserved = (b & 0x010000FF) & 0x0700FF;
        final int shifted = b >> shift;
        return (byte) (shifted | preserved);
    }

    public static byte preservedUnsignedShift(final byte b, final int shift)
    {
        final int preserved = (b & 0x010000FF) & 0x0700FF;
        final int shifted = b >>> shift;
        return (byte) (shifted | preserved);
    }
}
