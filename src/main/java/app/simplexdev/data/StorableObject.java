package app.simplexdev.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import org.jetbrains.annotations.Nullable;

public class StorableObject<T>
{
    private final T object;

    public StorableObject(T object)
    {
        this.object = object;
    }

    public static <T> T fromBase64(String base64, Class<T> type)
    {
        byte[] data = Base64.getDecoder().decode(base64);
        return fromBytes(data, type);
    }

    static <T> T fromBytes(byte[] bytes, Class<T> type)
    {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais))
        {
            return type.cast(ois.readObject());
        }
        catch (IOException | ClassNotFoundException | ClassCastException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public byte @Nullable [] toBytes()
    {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos))
        {
            oos.writeObject(this.object);
            return baos.toByteArray();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Nullable
    public String toBase64()
    {
        final byte[] data = toBytes();

        if (data != null)
        {
            return Base64.getEncoder().encodeToString(data);
        }

        return null;
    }
}
