package jobs;

import models.Subscription;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {

        // Check if the database is empty and we are in dev now
        if(Play.mode == Play.Mode.DEV && Subscription.count() == 0) {
            Fixtures.loadModels("initial-data.yml");
        }
    }
}
