package example;

/**
 * Average example
 * @author Aarthi
 */
public class Average {

        /**
         * Returns the average of an array of numbers
         * @param the array of integer numbers
         * @return the average of the numbers
         */
        public float average(int[] nums) {
            float result = 0;
            // Add your code
            // if empty
            if (nums.length == 0) {
            	result = 0;
            	return result;	
            }
            for (int i = 0; i < nums.length; i++) {
            	result = result + nums[i];
            }
            result = result/nums.length;
            return result;
        }

        public static void main(String[] args) {
            // Add your code
        	int[] data = {1,2,3,4,5,6,7,8,9,10};
        	Average avg = new Average();
        	float res = avg.average(data);
        	System.out.println(res);
        }
}
