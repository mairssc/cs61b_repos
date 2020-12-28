import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        OffByOne o = new OffByOne();
        OffByN offby5 = new OffByN(5);
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome(null));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("aaaa"));
        assertTrue(palindrome.isPalindrome("flake", o));
        assertTrue(palindrome.isPalindrome("a", o));
        assertFalse(palindrome.isPalindrome("azdvd", o));
        assertTrue(palindrome.isPalindrome("tat", null));
        assertTrue(palindrome.isPalindrome("tinny", offby5));
        assertTrue(palindrome.isPalindrome("axf", offby5));
        assertFalse(palindrome.isPalindrome("safahjfhds", offby5));
    }
}