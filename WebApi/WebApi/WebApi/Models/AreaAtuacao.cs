using Newtonsoft.Json;
using System;
using System.Collections.Generic;

namespace WebApi.Models
{
    public partial class AreaAtuacao
    {
        public AreaAtuacao()
        {
            AreaAtendimento = new HashSet<AreaAtendimento>();
        }

        [JsonProperty("cd_areaatuacao")]
        public int CdAreaatuacao { get; set; }
        [JsonProperty("ds_areaatuacao")]
        public string DsAreaatuacao { get; set; }
        [JsonProperty("dt_cadastro")]
        public DateTime DtCadastro { get; set; }
        [JsonProperty("dt_atualizacao")]
        public DateTime? DtAtualizacao { get; set; }

        [JsonIgnore]
        public ICollection<AreaAtendimento> AreaAtendimento { get; set; }
    }
}
