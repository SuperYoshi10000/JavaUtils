package local.ytk.util.reflect.builder;

import local.ytk.util.reflect.ClassType;
import local.ytk.util.reflect.ExtensionStatus;
import local.ytk.util.reflect.Visibility;

public record ElementModifiers<T>(
        ClassType classType,
        Class<T> targetType,
        Visibility visibility,
        ExtensionStatus extensionStatus
) {
    public ElementModifiers {
        if (!classType.canExtend() && extensionStatus != ExtensionStatus.FINAL)
            throw new IllegalArgumentException(classType.name + " must be final");
        if (!classType.canBeFinal() && extensionStatus == ExtensionStatus.FINAL) throw new IllegalArgumentException();
        if ((classType == ClassType.NON_CLASS) == (targetType == null)) throw new IllegalArgumentException();
    }
    
    @Override
    public String toString() {
        return "" + visibility + extensionStatus + (classType == ClassType.NON_CLASS ? targetType : classType);
    }
    public String toStringAssumedFinal() {
        return "" + visibility + classType;
    }
}