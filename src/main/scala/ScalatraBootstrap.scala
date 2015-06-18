import com.clackjones.connectivitymap.rest.{MainController, QuerySignatureController}
import com.clackjones.connectivitymap.service.FileBasedQuerySignatureProviderComponent
import org.scalatra.LifeCycle
import javax.servlet.ServletContext


class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {

    // mount servlets like this:
    context mount (new MainController, "/")
    context mount (new QuerySignatureController with FileBasedQuerySignatureProviderComponent, "/querysignature/*")
  }
}