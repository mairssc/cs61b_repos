public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }

        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            if (d.removeLast() != d.removeFirst()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        } else if (cc == null) {
            return isPalindrome(word);
        }

        Deque<Character> d = wordToDeque(word);
        while (d.size() > 1) {
            if (!cc.equalChars(d.removeLast(), d.removeFirst())) {
                return false;
            }
        }
        return true;
    }
}
