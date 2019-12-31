package com.leishb.leetcode.design;

/**
 * Created by me on 2019/12/2.
 */
public class _308_Range_Sum_Query_2D_Mutable {


    SegmentTree root = null;


    /**
     * Accpeted
     * @param matrix
     */
    public _308_Range_Sum_Query_2D_Mutable(int[][] matrix) {
        if (matrix.length==0) return;
        int m = matrix.length, n = matrix[0].length;
        root = buildTree(matrix, 0, m-1, 0, n-1);
    }

    public void update(int row, int col, int val) {
        update(root, row, col, val);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return query(root, row1, row2, col1, col2);
    }


    class SegmentTree{
        int rowStart;
        int rowEnd;
        int colStart;
        int colEnd;
        int value;
        SegmentTree upLeft;
        SegmentTree upRight;
        SegmentTree downLeft;
        SegmentTree downRight;
        SegmentTree(int rowStart, int rowEnd, int colStart, int colEnd, int value){
            this.rowStart = rowStart;
            this.rowEnd = rowEnd;
            this.colStart = colStart;
            this.colEnd = colEnd;
            this.value = value;
        }
    }



    private SegmentTree buildTree(int[][] matrix, int rowStart, int rowEnd, int colStart, int colEnd){
        if (rowStart>rowEnd || colStart > colEnd) return null;
        if (rowStart==rowEnd && colStart==colEnd){
            return new SegmentTree(rowStart, rowEnd, colStart, colEnd, matrix[rowStart][colStart]);
        }
        int midRow = (rowStart+rowEnd)/2;
        int midCol = (colStart+colEnd)/2;
        SegmentTree root = new SegmentTree(rowStart, rowEnd, colStart, colEnd, 0);
        root.upLeft = buildTree(matrix, rowStart, midRow, colStart, midCol);
        root.upRight = buildTree(matrix, rowStart, midRow, midCol+1, colEnd);
        root.downLeft = buildTree(matrix, midRow+1, rowEnd, colStart, midCol);
        root.downRight = buildTree(matrix, midRow+1, rowEnd, midCol+1, colEnd);
        int sum = 0;
        if (root.upLeft!=null) sum+=root.upLeft.value;
        if (root.upRight!=null) sum+=root.upRight.value;
        if (root.downLeft!=null) sum+=root.downLeft.value;
        if (root.downRight!=null) sum+=root.downRight.value;
        root.value = sum;
        return root;
    }


    private void update(SegmentTree root, int row, int col, int val){
        if (root==null) return ;
        if (root.rowEnd < row || root.rowStart > row || root.colStart > col || root.colEnd < col) return;
        if (root.rowStart == root.rowEnd && root.colStart == root.colEnd && root.rowStart == row && root.colStart == col){
            root.value = val;
            return;
        }
        update(root.upLeft, row, col, val);
        update(root.upRight, row, col, val);
        update(root.downLeft, row, col, val);
        update(root.downRight, row, col, val);
        int sum = 0;
        if (root.upLeft!=null) sum+=root.upLeft.value;
        if (root.upRight!=null) sum+=root.upRight.value;
        if (root.downLeft!=null) sum+=root.downLeft.value;
        if (root.downRight!=null) sum+=root.downRight.value;
        root.value = sum;
    }

    private int query(SegmentTree root, int rowStart, int rowEnd, int colStart, int colEnd){
        if (rowStart>rowEnd || colStart>colEnd || root==null) return 0;
        if (root.rowStart >= rowStart && root.rowEnd<=rowEnd && root.colStart >= colStart && root.colEnd <= colEnd){
            return root.value;
        }
        int sum = 0;
        sum += query(root.upLeft, rowStart, rowEnd, colStart, colEnd);
        sum += query(root.upRight, rowStart, rowEnd, colStart, colEnd);
        sum += query(root.downLeft, rowStart, rowEnd, colStart, colEnd);
        sum += query(root.downRight, rowStart, rowEnd, colStart, colEnd);
        return sum;
    }



    public static void main(String[] args){
        int[][] sum = new int[][]{{0,1},{1,0}};
        _308_Range_Sum_Query_2D_Mutable mutable = new _308_Range_Sum_Query_2D_Mutable(sum);
        mutable.update(1,1,3);
        System.out.println(mutable.sumRegion(0, 0, 1, 1));
    }
}
