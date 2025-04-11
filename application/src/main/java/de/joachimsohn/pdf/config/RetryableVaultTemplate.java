package de.joachimsohn.pdf.config;

/*TODO: @Component("vaultTemplate")
public final class RetryableVaultTemplate extends VaultTemplate {

    private final RetryTemplate retryTemplate;

    public RetryableVaultTemplate(VaultEndpointProvider endpointProvider,
                                  ClientHttpRequestFactory clientHttpRequestFactory,
                                  SessionManager sessionManager, ObjectProvider<RetryTemplate> retryTemplate) {
        super(endpointProvider, clientHttpRequestFactory, sessionManager);
        this.retryTemplate = retryTemplate.getIfAvailable();
    }

    @Override
    public @NotNull VaultResponse read(final @NotNull String path) {
        return retryTemplate.execute(context -> RetryableVaultTemplate.super.read(path));
    }

    @Override
    public <T> VaultResponseSupport<T> read(final @NotNull String path, final @NotNull Class<T> responseType) {
        return retryTemplate.execute(context -> RetryableVaultTemplate.super.read(path, responseType));
    }
}*/
