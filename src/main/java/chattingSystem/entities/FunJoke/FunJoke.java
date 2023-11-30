package chattingSystem.entities.FunJoke;

public class FunJoke {

    private String jokeQuestion;
    private String jokeAnswer;

    public FunJoke(String jokeQuestion, String jokeAnswer){
        this.jokeQuestion = jokeQuestion;
        this.jokeAnswer = jokeAnswer;}

    public static FunJokeBuilder builder() {
        return new FunJokeBuilder();
    }

    public static class FunJokeBuilder {

        private String jokeQuestion;
        private String jokeAnswer;

        FunJokeBuilder(){}

        public FunJokeBuilder jokeQuestion(String jokeQuestion) {
            this.jokeQuestion = jokeQuestion;
            return this;
        }

        public FunJokeBuilder jokeAnswer(String jokeAnswer) {
            this.jokeAnswer = jokeAnswer;
            return this;
        }

        public FunJoke build() {
            return new FunJoke(jokeQuestion, jokeAnswer);
        }
    }

    @Override
    public String toString() {
        return  "\n" + " Joke Question: " + jokeQuestion + "\n" + "\n" +
                " Joke Answer: " + jokeAnswer + "\n";}
}
