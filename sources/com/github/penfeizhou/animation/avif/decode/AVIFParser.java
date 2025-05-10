package com.github.penfeizhou.animation.avif.decode;

import android.content.Context;
import com.github.penfeizhou.animation.avif.io.AVIFReader;
import com.github.penfeizhou.animation.io.Reader;
import com.github.penfeizhou.animation.io.StreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.aomedia.avif.android.AvifDecoder;

/* loaded from: classes.dex */
public class AVIFParser {

    static class FormatException extends IOException {
        FormatException() {
            super("AVIF Format error");
        }
    }

    public static boolean isAVIF(String str) {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(str);
            try {
                boolean isAVIF = isAVIF(new StreamReader(fileInputStream2));
                try {
                    fileInputStream2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return isAVIF;
            } catch (Exception unused) {
                fileInputStream = fileInputStream2;
                if (fileInputStream == null) {
                    return false;
                }
                try {
                    fileInputStream.close();
                    return false;
                } catch (IOException e2) {
                    e2.printStackTrace();
                    return false;
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean isAVIF(Context context, String str) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(str);
            boolean isAVIF = isAVIF(new StreamReader(inputStream));
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return isAVIF;
        } catch (Exception unused) {
            if (inputStream == null) {
                return false;
            }
            try {
                inputStream.close();
                return false;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean isAVIF(Context context, int i) {
        InputStream inputStream = null;
        try {
            inputStream = context.getResources().openRawResource(i);
            boolean isAVIF = isAVIF(new StreamReader(inputStream));
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return isAVIF;
        } catch (Exception unused) {
            if (inputStream == null) {
                return false;
            }
            try {
                inputStream.close();
                return false;
            } catch (IOException e2) {
                e2.printStackTrace();
                return false;
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean isAVIF(Reader reader) {
        try {
            return AvifDecoder.isAvifImage((reader instanceof AVIFReader ? (AVIFReader) reader : new AVIFReader(reader)).toDirectByteBuffer());
        } catch (IOException unused) {
            return false;
        }
    }
}
