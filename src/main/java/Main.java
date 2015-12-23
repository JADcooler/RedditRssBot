import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import rss.RomeHelper;
import rss.RssNews;
import rss.RssNewsEntry;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String [] args) throws IOException, FeedException, InterruptedException {

        Timer timer = new Timer();
        final TimerTask updateRssTimer = new TimerTask() {
            @Override
            public void run() {
                updateRss();
            }
        };
        timer.scheduleAtFixedRate(updateRssTimer, 0, 2*60*1000);

        RedditClientFactory auth = new RedditClientFactory();
        RedditClient reddit = auth.authenticate();

        FluentRedditClient fluent = new FluentRedditClient(reddit);

        Submission submission = fluent.subreddit("News4AtheistsBotTest").submit("http://www.google.de", "title");


        boolean running = true;
        while (running) {
            Thread.sleep(5000);
        }

    public static void updateRss() {
        System.out.println("updating rss...");
        RssNews rss = new RssNews("http://www.rawstory.com/rs/feed/");
        List<RssNewsEntry> rssNewsEntries = rss.getFilteredRssNewsEntries("a");

        for (RssNewsEntry rssNewsEntry : rssNewsEntries) {
            System.out.println(rssNewsEntry.getTitle());
        }
        System.out.println("done.");
    }


}
