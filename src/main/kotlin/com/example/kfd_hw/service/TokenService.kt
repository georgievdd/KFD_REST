import com.example.kfd_hw.database.entity.Role
import com.example.kfd_hw.model.UserPrincipal

interface TokenService {

    fun generateRefreshToken(userPrincipal: UserPrincipal): String

    fun generateAccessToken(userPrincipal: UserPrincipal): String

    fun extractEmail(token: String): String?

    fun extractRole(token: String): Role

    fun isExpired(token: String): Boolean

    fun createContext(token: String, role: Role): UserPrincipal
}