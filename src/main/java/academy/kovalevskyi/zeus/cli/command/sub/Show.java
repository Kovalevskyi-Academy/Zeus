package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.testing.annotation.CourseProvider;
import academy.kovalevskyi.testing.service.ContainerRequest;
import academy.kovalevskyi.testing.util.ContainerManager;
import academy.kovalevskyi.zeus.cli.group.CourseRequest;
import java.util.HashMap;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;

@Command(
    name = "show",
    description = "Show available courses or containers",
    mixinStandardHelpOptions = true)
public class Show implements Runnable {

  @ArgGroup(exclusive = false)
  private CourseRequest manager;

  @Override
  public void run() {
    if (manager == null) {
      printAvailableCourses();
    } else {
      printContainersByRequest();
    }
  }

  private void printContainersByRequest() {
    final var containers = ContainerManager.getContainers(manager.prepareRequest());
    final var provider = ContainerManager.initProvider(containers.get(0));
    System.out.printf("Course name - %s, key - %s%n", provider.name(), provider.key());
    System.out.printf("Found %d container(s) by your request:%n", containers.size());
    var template = "- %s (week %d, day %d, id %d)%n";
    containers.forEach(clazz -> {
      var container = ContainerManager.getAnnotation(clazz);
      var name = clazz.getName().substring(clazz.getName().lastIndexOf(".") + 1);
      System.out.printf(template, name, container.week(), container.day(), container.id());
    });
  }

  private void printAvailableCourses() {
    var providers = new HashMap<String, CourseProvider>();
    ContainerManager.getContainers().forEach(clazz -> {
      var provider = ContainerManager.initProvider(clazz);
      providers.put(provider.key(), provider);
    });
    System.out.println("Available courses:");
    var template = "- %s key - %s, containers - %d%n";
    for (var provider : providers.values()) {
      var containers = ContainerManager
          .getContainers(ContainerRequest.builder().course(provider.key()).build())
          .size();
      System.out.printf(template, provider.name(), provider.key(), containers);
    }
  }
}
