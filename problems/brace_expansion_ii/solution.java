import java.util.*;

class Solution {
    private String expr;
    private int pos;
    
    public List<String> braceExpansionII(String expression) {
        this.expr = expression;
        this.pos = 0;
        Set<String> result = parseUnion();
        return new ArrayList<>(result); 
    }
    
    private Set<String> parseUnion() {
        Set<String> result = new TreeSet<>();
        result.addAll(parseConcat());
        while (pos < expr.length() && expr.charAt(pos) == ',') {
            pos++; // skip ','
            result.addAll(parseConcat());
        }
        return result;
    }
    
    private Set<String> parseConcat() {
        List<Set<String>> factors = new ArrayList<>();
        while (pos < expr.length() && expr.charAt(pos) != ',' && expr.charAt(pos) != '}') {
            factors.add(parseFactor());
        }
        
        Set<String> result = new TreeSet<>();
        result.add("");
        for (Set<String> factor : factors) {
            Set<String> newResult = new TreeSet<>();
            for (String prefix : result) {
                for (String suffix : factor) {
                    newResult.add(prefix + suffix);
                }
            }
            result = newResult;
        }
        return result;
    }
    
    private Set<String> parseFactor() {
        if (expr.charAt(pos) == '{') {
            pos++;
            Set<String> result = parseUnion();
            pos++; 
            return result;
        } else {
            int start = pos;
            while (pos < expr.length() && Character.isLowerCase(expr.charAt(pos))) {
                pos++;
            }
            Set<String> result = new TreeSet<>();
            result.add(expr.substring(start, pos));
            return result;
        }
    }
}