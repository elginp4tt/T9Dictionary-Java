import java.util.Comparator;

class WordComparator implements Comparator<Word>{

    @Override
    public int compare(Word o1, Word o2) {
        if (o1.getUseFrequency() < o2.getUseFrequency()){
            return 1;
        } else if (o1.getUseFrequency() > o2.getUseFrequency()){
            return -1;
        }
        return 0;
    }


}