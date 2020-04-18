package by.andrus.riversappback.model;

public enum RoleName {
    ROLE_USER((byte) 1), ROLE_ADMIN((byte) 2);

    public final long id;

    RoleName(byte id) {
        this.id = id;
    }
}
