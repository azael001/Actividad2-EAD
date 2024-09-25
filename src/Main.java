import java.io.*;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

/*Actividad 2. Ficheros acceso aleatorio.

2) Realiza un programa java, cree el fichero anterior y posteriormente, que reciba del usuario
un número de empleado y visualice sus datos. Y en caso de que no exista, debe indicarlo en un
mensaje.
3) Realiza un programa en java que lea un identificador de empleado y un importe (extra), de
forma que se buscará a ese empleado y se modificará el salario sumándole la nueva cantidad
extra. Debe visualizarse el salario anterior y el nuevo (volviéndolo a leer, no mostrando lo que
hayas guardado en una variable ;-). Y en caso de que no exista, debe indicarlo en un mensaje.
4) Localiza de forma DIRECTA el teléfono del 4ª empleado y muéstralo por pantalla*/


           //Intento de escribir el archivo
        File f = new File("./src/Empleados2.dat");
        try {
            RandomAccessFile alea = new RandomAccessFile(f,"rw");

            String name[]={"David","Pepe","Mario","Maria","Miguel","Luis"};
            int num_emp[]={1,2,3,4,5,6,};
            String telefono[]={"123456789","987654321","135798642","975286421","123645978","978645312"};
            Double salario[]={170.23,1720.04,1985.45,1634.13,1928.04,2435.03};

            for(int i = 0; i<name.length; i++){
                alea.writeInt(num_emp[i]);
                StringBuffer buf = new StringBuffer(name[i]);
                buf.setLength(25);
                for(int j =  name[i].length(); j<25; j++){
                    buf.setCharAt(j,' ');
                } //Esto es para rellenar espacios
                alea.writeChars(buf.toString());
                StringBuffer bufTel = new StringBuffer(telefono[i]);
                bufTel.setLength(9);
                alea.writeChars(bufTel.toString());
                alea.writeDouble(salario[i]);

            }
            alea.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File fr = new File("./src/Empleados2.dat");
        System.out.println("Dime el número de un empleado y te mostraré sus datos");
        Scanner in = new Scanner(System.in);
        int empleado = in.nextInt();
        int comprobar=1;

        try {
            RandomAccessFile aleat = new RandomAccessFile(fr,"r");

            int id,pos=0;
            char cnombre[]= new char[25], aux;
            char ctel[]=new char[9],auxx;
            Double sal = 0.00;

            while(true){
                aleat.seek(pos);
                id= aleat.readInt();
                for (int i = 0; i<cnombre.length; i++){
                    aux= aleat.readChar();
                    cnombre[i]=aux;
                }
                String vnombre = new String(cnombre).trim(); //el trim es para quitar espacios
                for (int i = 0; i<ctel.length; i++){
                    auxx= aleat.readChar();
                    ctel[i]=auxx;
                }
                String vtel = new String (ctel);
                sal = aleat.readDouble();

                pos=pos+80;
                if (empleado==comprobar){
                    Empleados e = new Empleados(id,vnombre,vtel,sal);

                    System.out.println(e.toString());
                    break;
                }

                comprobar ++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("No existe el empleado");
        }



    }
}