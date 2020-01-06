class Word {
    private String word;
    private int useFrequency = 0;
    
    public Word(String word, int useFrequency){
        this.word = word;
        this.useFrequency = useFrequency;
    }

    public String getWord() {
        return this.word;
    }

    public int getUseFrequency() {
        return this.useFrequency;
    }
}