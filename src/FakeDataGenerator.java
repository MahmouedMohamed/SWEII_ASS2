import java.util.Random;

public class FakeDataGenerator {
    private Random rand = new Random(System.currentTimeMillis());
    private String[] Quotes = {"Hello From Other Side."
                                ,"I'm Gonna Make Him An Offer He Can't Refuse."
                                ,"Frankly, my dear, I don't give a damn."
                                ,"Toto, I've got a feeling we're not in Kansas anymore."
                                ,"Here's looking at you, kid."
                                ,"Go ahead, make my day."
                                ,"All right, Mr. DeMille, I'm ready for my close-up."
                                ,"May the Force be with you."
                                ,"Fasten your seatbelts. It's going to be a bumpy night."
                                ,"You talking to me?"
                                ,"What we've got here is failure to communicate."
                                ,"I love the smell of napalm in the morning."
                                ,"Love means never having to say you're sorry."
                                ,"The stuff that dreams are made of."
                                ,"E.T. phone home."
                                ,"They call me Mister Tibbs!"
                                ,"Rosebud."
                                ,"The pest one :How you doin'? 'joey'"};
    public String GetRandomQuotes()
    {
        int n = rand.nextInt(Quotes.length);
        return Quotes[n];
    }
}
