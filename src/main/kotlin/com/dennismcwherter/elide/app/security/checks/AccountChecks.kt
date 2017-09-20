package com.dennismcwherter.elide.app.security.checks

import com.dennismcwherter.elide.app.models.Account
import com.dennismcwherter.elide.app.security.context.AccountPrincipal
import com.yahoo.elide.security.ChangeSpec
import com.yahoo.elide.security.RequestScope
import com.yahoo.elide.security.checks.OperationCheck
import java.util.*

/**
 * Checks operating on Account objects.
 */
abstract class AccountChecks {

    class AccessingSelf : OperationCheck<Account>() {
        override fun ok(`object`: Account?, requestScope: RequestScope?, changeSpec: Optional<ChangeSpec>?): Boolean {
            return `object`?.let { account ->
                val principal = requestScope!!.user?.opaqueUser as AccountPrincipal?
                account.name?.let { name ->
                    name == principal?.name
                }
            } ?: false
        }
    }
}
