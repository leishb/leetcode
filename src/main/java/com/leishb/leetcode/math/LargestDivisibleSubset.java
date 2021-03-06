package com.leishb.leetcode.math;

import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by me on 2017/10/9.
 */
@DynamicProgramming
public class LargestDivisibleSubset {

    @Test
    public void test(){
        int[] nums = new int[]{849,61,224,453,433,257,282,93,826,441,164,854,195,506,628,916,197,340,482,305,721,412,542,719,947,333,472,48,514,168,64,362,580,288,814,364,544,448,809,888,972,927,434,830,554,349,26,894,682,966,594,36,540,556,508,587,69,27,651,782,958,290,596,321,185,395,129,258,634,184,67,737,415,11,982,597,692,190,915,335,81,470,842,668,538,868,670,320,825,311,267,497,495,13,626,752,646,430,935,63,24,73,5,426,764,304,967,31,638,400,500,831,419,390,698,88,874,398,679,370,244,691,347,996,688,776,751,657,932,614,14,710,730,317,28,760,306,427,611,136,845,352,284,58,681,90,277,132,126,624,171,253,905,273,443,914,205,408,489,243,293,600,900,720,211,35,564,562,219,848,326,880,767,54,899,531,131,421,784,694,578,949,898,985,875,163,469,455,425,368,526,635,754,442,549,324,641,226,951,520,992,356,242,887,158,663,545,599,605,583,71,119,891,573,610,451,363,892,686,532,296,988,51,344,529,582,940,543,603,836,201,33,135,103,895,346,897,79,955,249,318,238,355,769,615,695,254,953,783,971,325,925,575,125,111,428,566,505,263,648,217,994,886,231,503,593,999,287,521,723,676,473,857,998,716,533,922,795,107,8,174,513,436,285,447,653,630,890,860,484,210,768,203,235,792,270,65,462,673,464,846,101,334,780,286,718,762,175,577,245,138,901,867,571,161,714,280,106,592,778,968,413,420,746,225,153,883,372,62,422,281,265,358,209,206,677,621,181,367,437,978,530,693,683,417,547,183,609,689,463,823,440,861,745,1000,418,620,598,338,34,581,704,110,55,738,595,380,744,45,743,471,817,52,632,815,264,742,222,353,396,765,386,486,360,766,567,980,310,664,404,841,118,906,876,169,800,525,700,893,98,53,859,493,330,528,945,511,173,747,576,758,939,15,725,250,601,302,50,354,116,522,872,402,777,49,30,202,930,40,631,822,796,130,47,82,699,303,25,833,248,678,481,444,384,75,666,127,705,749,948,240,941,991,804,394,452,828,23,636,866,799,633,917,759,665,707,501,608,492,454,411,237,590,541,16,856,862,843,9,476,539,929,586,946,644,684,755,6,260,124,438,516,713,409,728,546,771,753,877,834,697,869,675,165,60,934,289,801,1,896,496,339,100,128,642,827,943,504,154,21,565,309,806,661,660,741,969,824,460,507,379,407,120,990,43,272,918,337,239,331,669,790,152,791,97,658,643,902,750,572,793,327,650,625,855,936,498,458,156,207,727,920,510,445,536,38,230,294,871,708,779,218,908,315,179,873,142,724,524,907,416,802,431,191,76,960,241,385,348,259,187,7,853,923,397,810,359,560,405,952,91,449,973,299,913,485,674,414,266,149,84,942,208,588,993,680,4,392,166,612,839,95,712,619,275,772,702,931,247,818,735,561,711,557,151,864};

        int[] nums2 = new int[]{1,2,3,4,5};

        int[] nums3 = new int[]{359,376,43,315,167,216,777,625,498,442,172,324,987,400,280,367,371,24,418,208,812,488,861,646,63,804,863,853,102,174,443,901,486,126,419,701,254,550,48,214,873,386,965,504,753,336,527,522,895,339,361,755,423,558,551,276,11,724,70,823,624,555,300,42,607,554,84,508,953,649,732,338,613,236,90,762,612,194,452,972,140,747,209,690,22,220,413,91,36,998,341,77,956,246,512,464,198,547,888,476,782,977,776,896,940,321,347,264,621,10,829,383,939,825,441,326,822,754,130,379,265,945,577,491,252,273,792,168,699,866,319,704,708,148,230,521,914,988,846,88,121,600,217,499,513,427,344,3,242,947,627,325,146,469,375,12,815,46,67,193,648,963,876,78,366,531,49,532,475,875,398,69,821,454,497,170,922,872,533,736,917,951,609,461,598,571,118,798,981,835,113,530,799,995,930,682,38,405,557,787,377,810,278,874,331,199,97,215,286,13,165,473,115,816,584,707,237,568,72,166,249,805,247,746,534,408,759,739,925,855,305,210,219,470,807,936,974,417,519,288,15,64,438,581,455,250,503,496,145,256,327,255,346,251,109,650,813,679,119,619,721,406,593,489,924,964,563,897,27,769,687,608,224,462,432,39,937,384,990,45,33,154,723,152,772,795,364,283,833,395,495,164,181,232,116,899,458,548,191,320,889,587,353,661,856,814,764,529,737,948,127,335,695,960,858,801,543,916,588,478,103,592,20,481,958,618,334,424,397,694,314,158,114,700,381,287,683,966,459,923,902,332,892,235,938,178,431,631,296,885,820,409,585,141,223,535,688,258,689,884,720,365,611,277,985,684,416,666,182,961,108,355,525,862,412,549,186,244,589,421,52,76,718,352,702,510,117,290,692,603,864,323,388,536,392,151,436,350,788,75,900,490,306,975,207,261,870,188,729,231,485,348,507,676,238,111,180,984,135,771,671,51,1,997,675,869,950,445,434,92,137,221,907,245,17,794,360,935,370,239,362,175,620,973,784,106,136,122,281,426,196,134,68,634,672,28,385,411,526,735,633,841,227,86,500,653,906,933,932,129,435,756,262,698,329,204,941,614,668,139,403,229,243,808,857,659,640,545,345,82,228,516,734,566,868,414,474,506,363,87,173,578,575,312,169,908,929,444,685,657,23,524,358,225,9,41,999,834,546,920,849,456,93,651,433,586,882,942,457,62,839,818,260,369,773,890,865,596,98,271,669,962,311,996,160,200,767,539,163,800,757,582,343,538,131,567,446,213,378,959,299,915,761,313,845,712,330,253,573,18,138,317,56,691,349,605,463,652,781,992,422,32,664,711,284,741,289,57,697,368,583,943,40,298,430,851,913,745,65,179,705,630,401,674,465,487,878,477,240,35,572,838,968,678,342,775,30,806,680,969,2,241,909,803,979,460,518,156,85,643,850,597,843,89};
        System.out.println(largestDivisibleSubsetWithDp(nums3));
        System.out.println(largestDivisibleSubsetWithDp(nums));
        System.out.println(largestDivisibleSubsetWithDp(nums2));
        System.out.println(largestDivisibleSubsetWithDp(new int[]{2,3,8,9,27}));

        //largestDivisibleSubset
        System.out.println(largestDivisibleSubset(nums3));
        System.out.println(largestDivisibleSubset(nums));
        System.out.println(largestDivisibleSubset(nums2));
        System.out.println(largestDivisibleSubset(new int[]{2,3,8,9,27}));

        System.out.println(largestDivisibleSubset(new int[]{1,2,3}));
    }


    /**
     * dp[n] 为最后一位是nums[n]的最大可整除长度
     * Accepted
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubsetWithDp(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        if (nums==null || nums.length==0){
            return result;
        }
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxIndex = 0;
        int maxLen = 1;
        for (int i=1; i< nums.length;i++){
            dp[i]=1;
            for (int j=i-1;j>=0;j--){
                if (nums[j]%nums[i]==0 || nums[i]%nums[j]==0){
                    if (dp[j]+1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                    }
                }
            }
            if (dp[i]>maxLen){
                maxIndex = i;
                maxLen = dp[i];
            }
        }

        //from nums[maxIndex] to 0, add every element belongs to the largest subset.
        //注意数组排序的效果体现，nums[i+1] = p * nums[i], nums[i+2]=q * nums[i+1] ==> nums[i+2] = p * q * nums[i]
        int temp = nums[maxIndex];
        int curDp = dp[maxIndex];
        for (int i=maxIndex;i>=0;i--){
            if (temp%nums[i]==0 && curDp == dp[i]){
                result.add(nums[i]);
                temp = nums[i];
                curDp--;
            }
        }
        Collections.sort(result);
        return result;
    }





    /**
     * Accepted
     * @param nums
     * @return
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int maxLen = 0;
        List<Integer> result = null;
        for (int i=0; i< n;i++){
            List<Integer> maxList = new ArrayList<Integer>();
            maxList.add(nums[i]);
            for (int j=0;j<n;j++){
                if (i!=j && canDivide2(maxList, nums[j])){
                    maxList.add(nums[j]);
                }
            }
            if (maxList.size() >= maxLen){
                maxLen = maxList.size();
                result = maxList;
            }
        }
        Collections.sort(result);
        return result;
    }

    private boolean canDivide(List<Integer> nums, int num){
        for (int n : nums){
            if (num%n !=0 && n%num !=0){
                return false;
            }
        }
        return true;
    }


    private boolean canDivide2(List<Integer> nums, int num){
        int first = nums.get(0);
        int last = nums.get(nums.size()-1);
        if ((num%first==0 || first%num==0) && (num%last==0 || last%num==0)){
            return true;
        }
        return false;
    }
}
