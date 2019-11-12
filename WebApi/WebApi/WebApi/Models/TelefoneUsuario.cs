using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class TelefoneUsuario
    {


        [JsonProperty("cd_telefone")]
        public int CdTelefone { get; set; }
        [JsonProperty("cd_usuario")]
        public int CdUsuario { get; set; }
        [JsonProperty("nr_telefone")]
        public string NrTelefone { get; set; }
        [JsonProperty("nr_ddd")]
        public string NrDdd { get; set; }
        [JsonProperty("ds_telefone")]
        public string DsTelefone { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }
        [JsonProperty("dt_atualizacao)")]
        public DateTime? DtAtualizacao { get; set; }

        [JsonIgnore]
        public Usuario CdUsuarioNavigation { get; set; }
    }
}
