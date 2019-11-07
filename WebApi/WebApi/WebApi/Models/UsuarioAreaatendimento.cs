using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class UsuarioAreaatendimento
    {
        [JsonProperty("cd_usuarioatendimento")]
        public int CdUsuarioatendimento { get; set; }
        [JsonProperty("cd_areaatendimento")]
        public int CdAreaatendimento { get; set; }
        [JsonProperty("cd_usuario")]
        public int CdUsuario { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }
        [JsonProperty("dt_atualizacao")]
        public DateTime? DtAtualizacao { get; set; }


        [JsonIgnore]
        public AreaAtendimento CdAreaatendimentoNavigation { get; set; }
        [JsonIgnore]
        public Usuario CdUsuarioNavigation { get; set; }
    }
}
