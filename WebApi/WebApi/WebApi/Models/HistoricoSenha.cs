using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class HistoricoSenha
    {
        [JsonProperty("cd_historicosenha")]
        public int CdHistoricosenha { get; set; }
        [JsonProperty("cd_usuario")]
        public int CdUsuario { get; set; }
        [JsonProperty("ds_senha")]
        public string DsSenha { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }

        [JsonIgnore]
        public Usuario CdUsuarioNavigation { get; set; }
    }
}
