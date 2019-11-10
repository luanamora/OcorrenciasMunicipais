using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class TelefoneAreaatendimento
    {
        [JsonProperty("cd_telefoneareaatendimento")]
        public int CdTelefoneareaatendimento { get; set; }
        [JsonProperty("cd_areaatendimento")]
        public int CdAreaatendimento { get; set; }
        [JsonProperty("nr_telefone")]
        public string NrTelefone { get; set; }
        [JsonProperty("nr_ddd")]
        public string NrDdd { get; set; }
        [JsonProperty("ds_telefone")]
        public string DsTelefone { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }
        [JsonProperty("dt_aualizacao")]
        public DateTime? DtAtualizacao { get; set; }


        [JsonIgnore]
        public AreaAtendimento CdAreaatendimentoNavigation { get; set; }
    }
}
