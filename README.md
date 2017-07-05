# encrypt-lang
encrypt-lang  a language for encrypt

for example:

baseString  =httpMethod + "&" +
                           url_encode(  base_uri ) + "&" +
                           sorted_query_params.each  { | k, v |
                                              url_encode ( k ) + "%3D" +
                                              url_encode ( v )
                                               }.join("%26")


signature = base64_encode( hash_hmac('sha1', baseString, appSec&tokenSec))

