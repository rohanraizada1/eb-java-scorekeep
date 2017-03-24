package scorekeep;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
  private static final Logger logger = LoggerFactory.getLogger("scorekeep.Utils");
  private static AmazonSNS snsclient = AmazonSNSClientBuilder.standard()
        .withRegion(Regions.fromName(System.getenv("AWS_REGION")))
        .build();
  /*
   * Send a notification email.
   */
  public static void sendNotification(String subject, String body) {
    String topicarn = System.getenv("NOTIFICATION_TOPIC");
    PublishRequest publishRequest = new PublishRequest(topicarn, body, subject);
    PublishResult publishResult = snsclient.publish(publishRequest);
    logger.info("Email sent: " + publishResult.getMessageId());
  }
}