import java.util.HashMap;
import java.util.Map;

class Solution1 {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        
        StringBuilder sb = new StringBuilder();
        
        // 添加符号
        if (numerator < 0 ^ denominator < 0) sb.append("-");
        
        // 整数部分
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        sb.append(num / den);
        
        // 计算余数
        long remainder = num % den;
        if (remainder == 0) return sb.toString(); // 如果可以整除，直接返回
        
        sb.append(".");
        
        // 小数部分
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                sb.insert(map.get(remainder), "(");
                sb.append(")");
                break;
            }
            map.put(remainder, sb.length());
            remainder *= 10;
            sb.append(remainder / den);
            remainder %= den;
        }
        
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        System.out.println(solution.fractionToDecimal(1, 2));  // 输出: "0.5"
        System.out.println(solution.fractionToDecimal(2, 1));  // 输出: "2"
        System.out.println(solution.fractionToDecimal(4, 333)); // 输出: "0.(012)"
    }
}
