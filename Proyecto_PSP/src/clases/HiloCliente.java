package clases;

import javax.crypto.*;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.security.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HiloCliente extends Thread{
    //Atributos
    private Socket socketCliente = new Socket();
    private JTextArea texto;
    boolean activo;
    private Cliente cliente;
    private Connection conexion;
    private Cipher desCipher;
    private SecretKey key;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    //Constructor
    public HiloCliente(Socket socketCliente, JTextArea texto, boolean activo) {
        this.socketCliente = socketCliente;
        this.texto = texto;
        this.activo = activo;
    }

    @Override
    public void run() {
        try {
            texto.append("\nSe ha conectado un usuario\n");
            oos = new ObjectOutputStream(socketCliente.getOutputStream());
            ois = new ObjectInputStream(socketCliente.getInputStream());
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/proyecto_psp", "root", "lgp200267");
            texto.append("Obteniendo claves con cifrado DES\n");
            //generamos la clave y la enviamos
            KeyGenerator keygen = KeyGenerator.getInstance("DES");
            key = keygen.generateKey();
            //enviamos la clave
            oos.writeObject(key);
            desCipher = Cipher.getInstance("DES");
            texto.append("Clave enviada\n");
            while (activo) {
                int opcion = (int) ois.readObject();
                switch (opcion) {
                    case 1:
                        texto.append("Ha iniciado la opción de inicio de sesión\n");
                        inicioSesion();
                        if (cliente.isAcierto()) {
                            menu();
                        }
                        break;
                    case 2:
                        texto.append("Ha iniciado la opción de registro\n");
                        registrarClienteNuevo();
                        break;
                    case 3:
                        //enviamos el documento con firma digital
                        firmaDigital();
                        break;
                    default:
                        texto.append("Ha elegido una opción no valida, no se puede realizar ninguna acción\n");
                }
            }
        } catch (IOException ignored) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void inicioSesion() {
        try {
            desCipher.init(Cipher.DECRYPT_MODE, key);
            //recogemos el login
            texto.append("Recuperando datos...\n");
            byte[] loginCifrado = (byte[]) ois.readObject();
            byte[] loginBytes = desCipher.doFinal(loginCifrado);
            ByteArrayInputStream bis = new ByteArrayInputStream(loginBytes);
            ObjectInputStream oisbytes = new ObjectInputStream(bis);
            cliente = (Cliente) oisbytes.readObject();
            texto.append("Comprobando datos para el inicio de sesión...\n");
            String query = "SELECT * FROM usuarios WHERE usuario = ? and contrasena = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, cliente.getUsuario());
            ps.setString(2, cliente.getContrasena());
            texto.append("Consultando a la base de datos...\n");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                texto.append("Usuario y contraseña correctos\n");
                cliente.setId(rs.getInt(1));
                System.out.println(cliente.getId());
                cliente.setAcierto(true);
            }
            if (!cliente.isAcierto()) {
                texto.append("Usuario y/o contraseña incorrectos\n");
            }
            ps.close();
            //lo comvertimos a bytes y ciframos
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oosbytes = new ObjectOutputStream(bos);
            oosbytes.writeObject(cliente);
            oosbytes.flush();
            byte[] loginbytes = bos.toByteArray();
            desCipher.init(Cipher.ENCRYPT_MODE, key);
            loginCifrado = desCipher.doFinal(loginbytes);
            oos.writeObject(loginCifrado);
            bos.close();
            oosbytes.close();
        } catch (IOException ignored) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void registrarClienteNuevo() {
        try {
            desCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] registrarseCifrado = (byte[]) ois.readObject();
            byte[] registraseBytes = desCipher.doFinal(registrarseCifrado);
            ByteArrayInputStream bis = new ByteArrayInputStream(registraseBytes);
            ObjectInputStream oisbytes = new ObjectInputStream(bis);
            NuevoCliente registrarse = (NuevoCliente) oisbytes.readObject();
            bis.close();
            oisbytes.close();
            texto.append("Registrando nuevo cliente: " + registrarse.getUsuario() + "\n");
            String query = "INSERT INTO usuarios(nombre, apellido, edad, email, usuario, contrasena) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, registrarse.getNombre());
            ps.setString(2, registrarse.getApellido());
            ps.setInt(3, registrarse.getEdad());
            ps.setString(4, registrarse.getEmail());
            ps.setString(5, registrarse.getUsuario());
            ps.setString(6, registrarse.getContrasena());
            texto.append("Se ha registrado un nuevo cliente: " + registrarse.getUsuario() + "\n");
            ps.execute();
            ps.close();
        } catch (IOException ignored) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void menu() {
        boolean repetir = true;
        while (repetir) {
            try {
                int opcion = (int) ois.readObject();
                switch (opcion) {
                    case 1:
                        texto.append(cliente.getUsuario() + " está accediendo a sus cuentas bancarias\n");
                        recuperarCuentasCliente();
                        break;
                    case 2:
                        recuperarCuentasCliente();
                        break;
                    case 3:
                        texto.append(cliente.getUsuario() + " está accediendo a Realizar transferencia\n");
                        realizarTransferencia();
                        break;
                    case 4:
                        texto.append(cliente.getUsuario() + " está accediendo a sus movimientos.\n");
                        recuperarMovimientos();
                        break;
                    case 5:
                        texto.append(cliente.getUsuario() + " está accediendo a crear cuenta nueva\n");
                        anadirCuenta();
                        break;
                    case 6:
                        repetir = false;
                        break;
                    default:
                        texto.append(cliente.getUsuario() + " ha elegido una opción no valida, no se puede realizar ninguna acción\n");
                }
            } catch (IOException ignored) {
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void recuperarCuentasCliente() {
        try {
            //recuperar las cuentas del usuario
            List<Cuenta> cuentas = new ArrayList<Cuenta>();
            String query = "SELECT * FROM cuentas WHERE idusuario = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, cliente.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getDouble(4));
                cuentas.add(cuenta);
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oosbytes = new ObjectOutputStream(bos);
            oosbytes.writeObject(cuentas);
            oosbytes.flush();
            byte[] cuentasbytes = bos.toByteArray();
            desCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cuentasCifradas = desCipher.doFinal(cuentasbytes);
            oos.writeObject(cuentasCifradas);
            bos.close();
            oosbytes.close();
        } catch (IOException ignored) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public void firmaDigital() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            KeyPair par = keyGen.generateKeyPair();
            PrivateKey clavepriv = par.getPrivate();
            PublicKey clavepub = par.getPublic();
            oos.writeObject(clavepub);
            String documento = "Documento con los terminos";
            Signature rsa = Signature.getInstance("SHA1WITHRSA");
            rsa.initSign(clavepriv);
            rsa.update(documento.getBytes());
            byte[] firma = rsa.sign();
            oos.writeObject(firma);
        } catch (IOException ignored) {
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public void anadirCuenta() {
        try {
            desCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] ncifrado = (byte[]) ois.readObject();
            String numero = new String(desCipher.doFinal(ncifrado));
            String query = "SELECT * FROM cuentas WHERE numerocuenta = ?";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setString(1, numero);
            texto.append(cliente.getUsuario() + ": comprobando si el número de cuenta existe\n");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                texto.append(cliente.getUsuario() + ": el número de cuenta ya existe, no se puede añadir la cuenta\n");
                oos.writeObject(false);
            } else {
                texto.append(cliente.getUsuario() + ": creando número de cuenta\n");
                String queryInsert = "INSERT INTO cuentas(numerocuenta, idtitular, saldo) VALUES(?, ?, ?)";
                PreparedStatement psInsert = conexion.prepareStatement(queryInsert);
                psInsert.setString(1, numero);
                psInsert.setInt(2, cliente.getId());
                psInsert.setDouble(3, 0);
                psInsert.execute();
                psInsert.close();
                texto.append(cliente.getUsuario() + ": número de cuenta creado\n");
                oos.writeObject(true);
            }
            rs.close();
            ps.close();
        } catch (IOException ignored) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void realizarTransferencia() {
        try {
            texto.append(cliente.getUsuario() + " está realizando una transferencia\n");
            byte[] transCifrada = (byte[]) ois.readObject();
            desCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] transBytes = desCipher.doFinal(transCifrada);
            ByteArrayInputStream bis = new ByteArrayInputStream(transBytes);
            ObjectInputStream oisbytes = new ObjectInputStream(bis);
            Transferencia transferencia = (Transferencia) oisbytes.readObject();
            //enviar codigo de seguridad aqui
            Random r = new Random();
            int numero = r.nextInt(1000, 9999);
            texto.append("El número de seguridad de " + cliente.getUsuario() + " es: " + numero + "\n");
            desCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] numeroCifrado = desCipher.doFinal(String.valueOf(numero).getBytes());
            oos.writeObject(numeroCifrado);
            byte[] numeroCifradoIntento = (byte[]) ois.readObject();
            desCipher.init(Cipher.DECRYPT_MODE, key);
            int numeroIntento = Integer.parseInt(new String(desCipher.doFinal(numeroCifradoIntento)));
            if (numeroIntento == numero) {
                oos.writeObject(true);
                //insertamos los datos
                String query = "INSERT INTO transferencias(numcuentaremitente, nuncuentadestinatario, concepto, cantidad, fecha) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement ps = conexion.prepareStatement(query);
                ps.setString(1, transferencia.getRemitente());
                ps.setString(2, transferencia.getDestinatario());
                ps.setString(3, transferencia.getConcepto());
                ps.setDouble(4, transferencia.getCantidad());
                ps.setString(5, String.valueOf(LocalDateTime.now()));
                ps.execute();
                //actulizamos las cuentas
                texto.append("Actualizando los saldos de las cuentas debido a la transferencia que ha realizado " + cliente.getUsuario() + "\n");
                query = "UPDATE cuentas SET saldo = saldo - ?  WHERE numcuenta = ?";
                ps = conexion.prepareStatement(query);
                ps.setDouble(1, transferencia.getCantidad());
                ps.setString(2, transferencia.getRemitente());
                ps.execute();
                query = "UPDATE cuentas SET saldo = saldo + ?  WHERE numcuenta = ?";
                ps = conexion.prepareStatement(query);
                ps.setDouble(1, transferencia.getCantidad());
                ps.setString(2, transferencia.getDestinatario());
                ps.execute();
                ps.close();
            } else {
                oos.writeObject(false);
            }
            bis.close();
            oisbytes.close();
        } catch (IOException ignored) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void recuperarMovimientos() {
        try {
            List<Transferencia> movimientos = new ArrayList<>();
            texto.append(cliente.getUsuario() + " está cargando los movimientos de su cuenta\n");
            String query = "SELECT * FROM tranferencias WHERE numcuentaremitente IN (SELECT numerocuenta FROM cuentas WHERE id = ?) OR numcuentadestinatario IN (SELECT numerocuenta FROM cuentas WHERE id = ?);";
            PreparedStatement ps = conexion.prepareStatement(query);
            ps.setInt(1, cliente.getId());
            ps.setInt(2, cliente.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transferencia transferencia = new Transferencia(rs.getString(2), rs.getString(3), rs.getString(5), rs.getDouble(6), rs.getString(4));
                movimientos.add(transferencia);
            }
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oosbytes = new ObjectOutputStream(bos);
            oosbytes.writeObject(movimientos);
            oosbytes.flush();
            byte[] cuentasbytes = bos.toByteArray();
            desCipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cuentasCifradas = desCipher.doFinal(cuentasbytes);
            texto.append("Enviando datos de las tranferencias de " + cliente.getUsuario() + "\n");
            oos.writeObject(cuentasCifradas);
            bos.close();
            oosbytes.close();
        } catch (IOException ignored) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
