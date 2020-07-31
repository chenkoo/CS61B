package hw3.hash;

import java.lang.reflect.Array;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int N = oomages.size();
        int min = 1;
        int[] mArray = new int[1];
        for (Oomage oo : oomages) {
            int bucketNum = (oo.hashCode() & 0x7FFFFFFF) % M;
            if (bucketNum >= mArray.length) {
                int[] temp = new int[bucketNum + 1];
                min += (temp.length - mArray.length);
                System.arraycopy(mArray, 0, temp, 0, mArray.length);
                mArray = temp;
            }
            mArray[bucketNum] += 1;
            if (mArray[bucketNum] > N / 2.5) return false;
            if (mArray[bucketNum] - 1 < N / 50.0 && mArray[bucketNum] >= N / 50.0) min -= 1;
        }
        if (min > 0) return false;
        return true;
    }
}
