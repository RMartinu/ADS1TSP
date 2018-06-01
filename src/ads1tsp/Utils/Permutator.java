/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1tsp.Utils;

/**
 *
 * @author Robert Martinu
 * @author Julia Pichler
 */
public class Permutator
{

    private int[] array;
    private int firstNum = 0;
    private boolean firstReady;

    public Permutator(int nodecount)
    {
        array = new int[nodecount];
        reset();
    }

    public void reset()
    {
        for (int i = 0; i < array.length; i++)
        {
            array[i] = i;
        }
        firstReady = false;
    }

    public boolean hasMore()
    {
        boolean end = firstReady;
        for (int i = 1; i < array.length; i++)
        {
            end = end && array[i] < array[i - 1];
        }
        return !end;
    }

    /**
     * swapping indices to generate next permutation
     * @return returns next permutation
     */
    
    public int[] getNext()
    {
        if (!firstReady)
        {
            firstReady = true;
            return array;
        }
        int temp;
        int j = array.length - 2;
        int k = array.length - 1;

        for (; array[j] > array[j + 1]; j--);
        for (; array[j] > array[k]; k--);

        temp = array[k];
        array[k] = array[j];
        array[j] = temp;

        int r = array.length - 1;
        int s = j + 1;
        while (r > s)
        {
            temp = array[s];
            array[s++] = array[r];
            array[r--] = temp;
        }

        return array;
    }

}
