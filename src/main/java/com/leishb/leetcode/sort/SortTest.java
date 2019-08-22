package com.leishb.leetcode.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by me on 2019/2/28.
 */
public class SortTest {



    public void insertSort(int[] arr){
        for (int i=1;i< arr.length; i++){
            int j = i-1;
            int num = arr[i];
            while (j >= 0 && arr[j] > num){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = num;
        }
    }



    public void selectSort(int[] arr){
        for (int i=0;i < arr.length; i++){
            int minIndex = i;
            for (int j=i;j<arr.length;j++){
                if (arr[j] < arr[minIndex]){
                    minIndex = j;
                }
            }
            if (minIndex!=i){
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }


    public void bubbleSort(int[] arr){
        for (int i=0 ;i< arr.length; i++){
            boolean flag = false;
            for (int j=0; j< arr.length - i -1;j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag){
                break;
            }
        }
    }



    public void mergeSort(int[] arr, int left, int right){
        if (left<right){
            int mid = (left+right)/2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid+1, right);
            merge(arr, left, mid, right);
        }
    }



    public void merge(int[] arr, int left, int mid, int right){
        int[] copy = new int[right-left+1];
        int i=left;
        int j=mid+1;
        int k=0;
        while (i<=mid && j<=right){
            while (i<=mid && arr[i] <= arr[j]){
                copy[k++] = arr[i++];
            }
            while (j<=right && arr[j] < arr[i]){
                copy[k++] = arr[j++];
            }
        }
        while (i<=mid){
            copy[k++] = arr[i++];
        }
        while (j<=right){
            copy[k++] = arr[j++];
        }
        i=0;
        for (;i<copy.length;i++){
            arr[left++] = copy[i];
        }
    }



    public void quickSort(int[] arr, int left, int right){
        if (left<right){
            int p = partition2(arr, left, right);
            quickSort(arr, left, p-1);
            quickSort(arr, p+1, right);
        }
    }


    private int partition(int[] arr, int left, int right){
        int k = left;
        int pivot = arr[left];
        while (left<=right){
            while (left<=right && pivot >=arr[left]){
                left++;
            }
            while (left<=right && pivot < arr[right]){
                right--;
            }
            if (left<right){
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        arr[k] = arr[right];
        arr[right] = pivot;
        return right;
    }



    private int partition2(int[] arr, int left, int right){
        int pivot = arr[right];
        int i= left;
        for (int j=left;j<=right-1;j++){
            if (arr[j] < pivot){
                if (i!=j){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
                i++;
            }
        }
        int temp = arr[i];
        arr[i] = pivot;
        arr[right] = temp;
        return i;
    }


    public int selectK(int[] arr, int start, int end, int k){
        int p = partition(arr, start, end);
        if (k > p-start+1){
            return selectK(arr, p+1, end, k+start-p-1);
        }else if (k < p-start+1){
            return selectK(arr, start, p-1, k);
        }else {
            return arr[p];
        }
    }



    public int selectK(int[] arr, int k){
        for (int i=0;i<k;i++){
            int p = i;
            while (p > 0 && arr[p] < arr[(p-1)/2]){
                swap(arr, p, (p-1)/2);
                p = (p-1)/2;
            }
        }
        for (int i=k;i<arr.length;i++){
            if(arr[i] <= arr[0]){
                continue;
            }
            swap(arr, 0, i);
            int p = 0;
            while (p < k){
                int l = 2*p+1;
                int r = l+1;
                int min = p;
                if (l < k && arr[l] < arr[min]){
                    min = l;
                }
                if (r < k && arr[r] < arr[min]){
                    min = r;
                }
                if (min == p){
                    break;
                }else {
                    swap(arr, min, p);
                    p = min;
                }
            }
        }
        return arr[0];
    }





    public void heapSort(int[] arr){
        buildHeap(arr);
        int size = arr.length;
        while (size >0){
            swap(arr, 0, --size);
            sink(arr, size, 0);
        }
    }


    private void buildHeap(int[] arr){
        for (int i=0;i<arr.length;i++){
            swim(arr, i);
        }
    }


    private void swim(int[] arr, int k){
        while(k >0 && arr[k] > arr[(k-1)/2]){
            swap(arr, k, (k-1)/2);
            k = (k-1)/2;
        }
    }


    private void sink(int[] arr, int size, int k){
        while (k < size){
            int l = 2*k+1;
            int r = l+1;
            int max = k;
            if (l < size && arr[l] > arr[max]){
                max = l;
            }
            if (r < size && arr[r] > arr[max]){
                max = r;
            }
            if (max == k){
                break;
            }else {
                swap(arr, max, k);
                k = max;
            }
        }
    }

    private void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    @Test
    public void testSort(){
        int[] arr1 = new int[100];
        int[] arr2 = new int[100];
        int[] arr3 = new int[100];
        int[] arr4 = new int[100];
        int[] arr5 = new int[100];
        int[] arr6 = new int[100];
        int[] arr8 = new int[100];

        for (int i=0;i<100;i++){
            arr1[i] = new Random().nextInt(100);
            arr2[i] = arr1[i];
            arr3[i] = arr1[i];
            arr4[i] = arr1[i];
            arr5[i] = arr1[i];
            arr6[i] = arr1[i];
            arr8[i] = arr1[i];
        }

        insertSort(arr1);
        Arrays.sort(arr2);
        selectSort(arr3);
        bubbleSort(arr4);
        mergeSort(arr5, 0, arr5.length-1);
        quickSort(arr6, 0, arr6.length-1);
        heapSort(arr8);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        System.out.println(Arrays.toString(arr4));
        System.out.println(Arrays.toString(arr5));
        System.out.println(Arrays.toString(arr6));
        System.out.println(Arrays.toString(arr8));

        Assert.assertTrue(Arrays.equals(arr2, arr1));
        Assert.assertTrue(Arrays.equals(arr2, arr3));
        Assert.assertTrue(Arrays.equals(arr2, arr4));
        Assert.assertTrue(Arrays.equals(arr2, arr5));
        Assert.assertTrue(Arrays.equals(arr2, arr6));
        Assert.assertTrue(Arrays.equals(arr2, arr8));
    }


    @Test
    public void testSelectK(){
        int[] arr1 = new int[100];
        int[] arr2 = new int[100];

        for (int i=0;i<100;i++){
            arr1[i] = new Random().nextInt(100);
            arr2[i] = arr1[i];
        }

        Arrays.sort(arr1);

        for (int i=0;i<arr1.length;i++){
            Assert.assertTrue(selectK(arr2, 0, 99, i+1) == arr1[i]);
            Assert.assertTrue(selectK(arr2, i+1) == arr1[100-i-1]);
        }
    }
}
