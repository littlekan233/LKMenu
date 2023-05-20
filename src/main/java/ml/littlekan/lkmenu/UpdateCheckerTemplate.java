package ml.littlekan.lkmenu;

import java.io.Serializable;
import lombok.Data;

@Data
public class UpdateCheckerTemplate implements Serializable{
    private boolean prerelease;
    private String tag_name;
}
