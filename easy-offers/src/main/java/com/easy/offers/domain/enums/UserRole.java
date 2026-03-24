package com.easy.offers.domain.enums;
// Enum del dominio: define roles de usuario del sistema.

public enum UserRole {
    ADMIN,
    // Usuario con privilegios administrativos
    // Puede gestionar el sistema completo
    EMPLOYEE;
    // Usuario estándar (empleado)
    // Acceso limitado

    // Devuelve el rol en formato compatible con Spring Security
    public String getAuthority() {
        return "ROLE_" + this.name();
        // Spring Security usa el prefijo "ROLE_"
        // this.name() devuelve el nombre del enum:
        // ADMIN → "ADMIN"
        // EMPLOYEE → "EMPLOYEE"

        // Resultado:
        // ADMIN → "ROLE_ADMIN"
        // EMPLOYEE → "ROLE_EMPLOYEE"
    }
}
