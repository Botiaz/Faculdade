import java.io.*;
import java.net.*;

class TP01Q07 {
    public static String getHtml(String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here

        }

        return resp;
    }

    public static boolean stop(String texto) {
        boolean resp = true;
        if (texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M') {
            resp = false;
        }
        return resp;
    }

    public static void contador(String html, String titulo) {
        int valores[] = new int[25];

        for (int i = 0; i < 25; i++) {
            valores[i] = 0;
        }

        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == '\u0061') {
                valores[0] += 1;
            } else if (html.charAt(i) == '\u0065') {
                valores[1] += 1;
            } else if (html.charAt(i) == '\u0069') {
                valores[2] += 1;
            } else if (html.charAt(i) == '\u006f') {
                valores[3] += 1;
            } else if (html.charAt(i) == '\u0075') {
                valores[4] += 1;
            } else if (html.charAt(i) == '\u00e1') {
                valores[5] += 1;
            } else if (html.charAt(i) == '\u00e9') {
                valores[6] += 1;
            } else if (html.charAt(i) == '\u00ed') {
                valores[7] += 1;
            } else if (html.charAt(i) == '\u00f3') {
                valores[8] += 1;
            } else if (html.charAt(i) == '\u00fa') {
                valores[9] += 1;
            } else if (html.charAt(i) == '\u00e0') {
                valores[10] += 1;
            } else if (html.charAt(i) == '\u00e8') {
                valores[11] += 1;
            } else if (html.charAt(i) == '\u00ec') {
                valores[12] += 1;
            } else if (html.charAt(i) == '\u00f2') {
                valores[13] += 1;
            } else if (html.charAt(i) == '\u00f9') {
                valores[14] += 1;
            } else if (html.charAt(i) == '\u00e3') {
                valores[15] += 1;
            } else if (html.charAt(i) == '\u00f5') {
                valores[16] += 1;
            } else if (html.charAt(i) == '\u00e2') {
                valores[17] += 1;
            } else if (html.charAt(i) == '\u00ea') {
                valores[18] += 1;
            } else if (html.charAt(i) == '\u00ee') {
                valores[19] += 1;
            } else if (html.charAt(i) == '\u00f4') {
                valores[20] += 1;
            } else if (html.charAt(i) == '\u00fb') {
                valores[21] += 1;
            } else if (html.charAt(i) == '<') {
                if (html.charAt((i + 1)) == 'b' && html.charAt((i + 2)) == 'r' && html.charAt((i + 3)) == '>') {
                    valores[23] += 1;
                } else if (html.charAt((i + 1)) == 't' && html.charAt((i + 2)) == 'a' && html.charAt((i + 3)) == 'b'
                        && html.charAt((i + 4)) == 'l' && html.charAt((i + 5)) == 'e' && html.charAt((i + 6)) == '>') {
                    valores[24] += 1;
                    valores[22] -= 3;
                    valores[1] -= 1;
                    valores[0] -= 1;
                }

            } else if ((html.charAt(i) >= 'a' && html.charAt(i) <= 'z')) {
                valores[22] += 1;
            }
        }

        MyIO.println("a(" + valores[0] + ") e(" + valores[1] + ") i(" + valores[2] + ") o(" + valores[3] + ") u("
                + valores[4] + ") á(" + valores[5] + ") é(" + valores[6] + ") í(" + valores[7] + ") ó(" + valores[8]
                + ") ú(" + valores[9] + ") à(" + valores[10] + ") è(" + valores[11] + ") ì(" + valores[12] + ") ò("
                + valores[13] + ") ù(" + valores[14] + ") ã(" + valores[15] + ") õ(" + valores[16] + ") â("
                + valores[17] + ") ê(" + valores[18] + ") î(" + valores[19] + ") ô(" + valores[20] + ") û("
                + valores[21] + ") consoante(" + valores[22] + ") <br>(" + valores[23] + ") <table>(" + valores[24]
                + ") " + titulo);
    }

    public static void main(String[] args) {
        String titulo = new String();
        String url = new String();

        titulo = MyIO.readLine();

        while (stop(titulo)) {
            url = MyIO.readLine();
            String html = getHtml(url);
            contador(html, titulo);
            titulo = MyIO.readLine();
        }
    }

}