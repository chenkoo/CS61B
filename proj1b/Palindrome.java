public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    private String dequeToString(Deque<Character> d) {
        String actual = "";
        int size = d.size();
        for (int i = 0; i < size; i++) {
            actual += d.removeFirst();
        }
        return actual;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        if (d.size() == 0 || d.size() == 1) {
            return true;
        } else {
            char f = d.removeFirst();
            char l = d.removeLast();
            if (f == l) {
                return isPalindrome(dequeToString(d));
            }
            return false;
        }
    }

    private boolean isPalindrome(Deque<Character> wordInDeque, CharacterComparator cc) {
        while (wordInDeque.size() > 1) {
            return cc.equalChars(wordInDeque.removeFirst(), wordInDeque.removeLast())
                    && isPalindrome(wordInDeque, cc);
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

}
