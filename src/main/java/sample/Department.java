package sample;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Version;

@Entity(immutable = true)
public class Department {
  @Id private final Integer id;
  private final String name;
  @Version private final Integer version;

  public Department(Integer id, String name, Integer version) {
    this.id = id;
    this.name = name;
    this.version = version;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Integer getVersion() {
    return version;
  }
}
