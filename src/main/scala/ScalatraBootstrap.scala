import com.clackjones.connectivitymap.cmap.ConnectivityMapModule
import com.clackjones.connectivitymap.querysignature.DefaultRandomSignatureGeneratorComponent
import com.clackjones.connectivitymap.referenceprofile.{ReferenceProfileFileLoaderComponent, ReferenceSetCreatorByDrugDoseAndCellLineComponent, ReferenceSetFileLoaderComponent}
import com.clackjones.connectivitymap.rest._
import com.clackjones.connectivitymap.service._
import com.clackjones.connectivitymap.spark.SparkContextComponent
import org.scalatra.LifeCycle
import javax.servlet.ServletContext


class ScalatraBootstrap extends LifeCycle
      with InMemoryExperimentProviderComponent
      with PollingSparkExperimentRunnerComponent with DefaultRandomSignatureGeneratorComponent
      with ReferenceSetFileLoaderComponent with ReferenceProfileFileLoaderComponent
      with SparkQuerySignatureProviderComponent with SparkExperimentResultProviderComponent
      with SparkContextComponent
      with ConnectivityMapModule with FileBasedReferenceSetProviderComponent
      with ReferenceSetCreatorByDrugDoseAndCellLineComponent
      with QuerySignatureControllerComponent with ExperimentControllerComponent
      with ExperimentQueueComponent
      with ExperimentResultControllerComponent
      with ProgressControllerComponent
      with MainControllerComponent {

  override def init(context: ServletContext) {
    // mount servlets like this:
    context mount (mainController, "/")
    context mount (querySignatureController, "/querysignature/*")
    context mount (experimentController, "/experiment/*")
    context mount (experimentResultController, "/result/*")
    context mount (experimentResultController, "/result/*")
    context mount (progressController, "/progress/*")

    /* now generate the random gene signatures */
    experimentRunner.start()
  }
}
