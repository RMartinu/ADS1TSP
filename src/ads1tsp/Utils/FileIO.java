/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Robert Martinu
 * @author Julia Pichler
 */
public class FileIO
{

    /**
     * 
     * @param in file with town x and y coordinates
     * @return returns adjacent list object
     * @throws FileNotFoundException 
     */
    
    public static AdjacentList NodeListFromFile(File in) throws FileNotFoundException
    {

        int numberOfEntries = 0;

        System.out.println("Been asked o open;" + in.getAbsolutePath());
        Scanner sc = new Scanner(in);
        if (sc.hasNextInt())
        {
            numberOfEntries = sc.nextInt();
        } else
        {
            throw new FileNotFoundException("Unable to parse input file: NodeNumber");
        }
//        System.out.println(numberOfEntries);
//        System.err.println(sc.nextLine());
        Node[] tempInput = new Node[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++)
        {
            double x = 0, y = 0;
            if (sc.hasNext())
            {
                x = Double.parseDouble(sc.next());
//                System.out.print("X: " + x);
            } else
            {
//                System.err.println(sc.next());
                throw new FileNotFoundException("Unable to parse input file" + i + " th x");
            }

            if (sc.hasNext())
            {
                y = Double.parseDouble(sc.next());
//                System.out.println(", Y: " + y);
            } else
            {
                throw new FileNotFoundException("Unable to parse input file" + i + "th y");
            }
            Node n = new Node(x, y);
            tempInput[i] = n;
        }

        return new AdjacentList(tempInput);
    }

}
